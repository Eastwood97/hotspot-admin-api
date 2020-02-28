package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.ImportService;
import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tzm
 * @desc 布控号码的数据接口
 *
 */
@RestController
@RequestMapping("/admin/targetNum")
public class TargetNumController {
    private final Log logger= LogFactory.getLog(TargetNumController.class);

    @Autowired
    public TargetNumService targetNumService;

    @Autowired
    private ImportService importService;

    @GetMapping
    public Object getTargetNum(String targetName,String imsi,String imei,String isdn,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit
                          ){
        List<HotTargetInfo> targetNumList=targetNumService.query(page,limit,targetName,imsi,imei,isdn);
        return ResponseUtil.okList(targetNumList);
    }

    /**
     * 参数验证
     * @param targetInfo
     * @return
     */
    private Object validate(HotTargetInfo targetInfo){
        String targetName=targetInfo.getTargetName();
        if(StringUtils.isEmpty(targetName)){
            return ResponseUtil.badArgument();
        }

        String imsi=targetInfo.getImsi();
        String imei=targetInfo.getImei();
        String isdn=targetInfo.getIsdn();

        Boolean flagImsi=StringUtils.isEmpty(imsi);
        Boolean flagImei=StringUtils.isEmpty(imei);
        Boolean flagIsdn=StringUtils.isEmpty(isdn);

        //imsi imei  isdn 不能都为空
        if (flagImei&&flagImsi&&flagIsdn){
            return  ResponseUtil.badArgument();
        }

        return null;

    }

    /**
     * 添加目标号码
     * @param targetNum
     * @return
     */
    @PostMapping
    public Object add(@RequestBody HotTargetInfo targetNum){
        Object error=validate(targetNum);
        if(error!=null){
            return  error;
        }
        targetNumService.add(targetNum);
        return ResponseUtil.ok(targetNum);
    }

    /**
     * 编辑目标号的信息
     * @param targetInfo
     * @return
     */
    @PutMapping
    public Object Update(@RequestBody HotTargetInfo targetInfo){
        Object error=validate(targetInfo);
        if(error!=null){
            return  error;
        }
        if(targetNumService.updateById(targetInfo)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(targetInfo);
    }

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    @DeleteMapping
    public Object deleteById(@RequestBody String targetIds){
        if(targetNumService.deleteById(targetIds)){
            return  ResponseUtil.ok();
        }else {
            return  ResponseUtil.deleteDataFailed();
        }
    }

//    @RequestMapping(value = "/exportTarget", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> exportTarget() {
//        ResponseEntity<byte[]> targetExcel=PoiTargetUtil.exportTargetExcel(targetNumService.getAllTargetNum());
//        return ResponseUtil.okList(targetExcel);
//    }

    /**
     *
     * 功能描述: 上传文件excl
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Object uploadExcel(HttpServletRequest request) throws Exception {
        try{

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest )request;
            MultipartFile file = multipartRequest.getFile("file");
            String peopleid = multipartRequest.getParameter("body");
            if (file.isEmpty()) {
                return ResponseUtil.fail(508,"上传失败");
            }
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
            HotTargetInfo target=null;
            List<HotTargetInfo> targetList=new ArrayList<HotTargetInfo>();
            for (int i = 0; i < list.size(); i++) {
                List<Object> lo = list.get(i);
                String targetName=lo.get(0).toString();
                String imsi = lo.get(1).toString();
                String imei = lo.get(2).toString();
                String  isdn = lo.get(3).toString();
                String caseName = lo.get(4).toString();
                String desc = lo.get(5).toString();
                if(imsi.equals("")&&imei.equals("")&&isdn.equals("")){
                    return ResponseUtil.fail(401, "imsi,imei,isdn不能都为空!");
                }
                target=new HotTargetInfo();
                target.setImei(imei);
                target.setImsi(imsi);
                target.setIsdn(isdn);
                target.setCaseName(caseName);
                target.setDesc(desc);
                targetList.add(target);
            }
            if (targetNumService.insertForeach(targetList)){
                return ResponseUtil.ok();
            }else{
                return ResponseUtil.fail();
            }
        }catch (Exception e){
            return ResponseUtil.fail(508,"上传失败");
        }
    }
}
