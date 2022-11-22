package com.tq.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tq.springboot.entity.StringToJsonVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: tq
 * @Date: 2022/4/26
 * @Description
 * @Version: 1.0
 */
@SpringBootTest
public class JsonChange {
    @Test
    public void JsonMap(){
        Map<String, String> map = new HashMap<>();
        map.put("error_code", "10101");
        map.put("error", "token不正确");
        String s1 = map.toString();
        System.out.println(s1);


        String s="{\"errorStep\":null,\"innerErrorMsg\":null,\"request\":\"hq_call_log_realtime_sync\",\"error_code\":\"10101\",\"error\":\"token不正确\"}";

        /*JSONObject jsonObject = JSONObject.parseObject(s);
        Map maps2 =(Map)JSONObject.parseObject("{\"UNI_BSS_ATTACHED\":{\"MEDIA_INFO\":\"\"},\"UNI_BSS_BODY\":{\"CALL_LOG_REAL_TIME_SYN_RSP\":{\"RESP_CODE\":\"0000\",\"RESP_DESC\":\"操作成功\"}},\"UNI_BSS_HEAD\":{\"RESP_DESC\":\"成功\",\"APP_ID\":\"V1uXZOJxoh\",\"RESP_CODE\":\"00000\",\"TIMESTAMP\":\"2022-04-26 13:46:06 958\",\"TRANS_ID\":\"20220426134606958523621\"}}");

        Map maps = (Map) JSON.parse(s);

        maps.forEach((key,value)->{
            if("error_code".equals(key)){
                //调用出错,再次请求
                System.out.println("再次调用");
                return;

            }
            System.out.println(key + "--->"+value);
        });
*/
        //JSONObject o = JSONObject.parseObject(s);
        JSONObject o = JSONObject.parseObject("{\"UNI_BSS_ATTACHED\":{\"MEDIA_INFO\":\"\"},\"UNI_BSS_BODY\":{\"CALL_LOG_REAL_TIME_SYN_RSP\":{\"RESP_CODE\":\"0000\",\"RESP_DESC\":\"操作成功\"}},\"UNI_BSS_HEAD\":{\"RESP_DESC\":\"成功\",\"APP_ID\":\"V1uXZOJxoh\",\"RESP_CODE\":\"00000\",\"TIMESTAMP\":\"2022-04-26 13:46:06 958\",\"TRANS_ID\":\"20220426134606958523621\"}}");

        System.out.println(o.toJSONString());
       // JSONObject error_code = o.getJSONObject("error_code");
        Object error_code = o.get("error_code");
        System.out.println(error_code);
        /*
        //Map map1 =(Map) JSONObject.parseObject(s);

        JSONObject error_code = jsonObject.getJSONObject("error_code");
        System.out.println(error_code.toJSONString());*/

        System.out.println("-------------------------");
        JSONObject o2 = JSONObject.parseObject("{\n" +
                "    \"UNI_BSS_ATTACHED\": {\n" +
                "        \"MEDIA_INFO\": \"\"\n" +
                "    },\n" +
                "    \"UNI_BSS_BODY\": {\n" +
                "        \"CALL_LOG_REAL_TIME_SYN_RSP\": {\n" +
                "            \"RESP_CODE\": \"0000\",\n" +
                "            \"RESP_DESC\": \"操作成功\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"UNI_BSS_HEAD\": {\n" +
                "        \"RESP_DESC\": \"成功\",\n" +
                "        \"APP_ID\": \"V1uXZOJxoh\",\n" +
                "        \"RESP_CODE\": \"00000\",\n" +
                "        \"TIMESTAMP\": \"2022-05-13 15:45:03 011\",\n" +
                "        \"TRANS_ID\": \"20220513154503011095611\"\n" +
                "    }\n" +
                "}");
        String respCode = (String)o2.getJSONObject("UNI_BSS_HEAD").get("RESP_CODE");
        String respOptCode = (String)o2.getJSONObject("UNI_BSS_BODY").getJSONObject("CALL_LOG_REAL_TIME_SYN_RSP").get("RESP_CODE");
        System.out.println(respCode);
        System.out.println(respOptCode);
        System.out.println("dev修改");

    }

    @Test
    public void stringToJson(){
        String s="{\"id\":\"WZXJ1253\",\"text\":\"乐清盐盆纬八路铁塔\",\"site_id\":null,\"site_name\":null}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);

        String s2="[{\"id\":\"WZXJ1253\",\"text\":\"乐清盐盆纬八路铁塔\"}]";
        //StringToJsonVo stringToJsonVo = JSONObject.parseObject(s2, StringToJsonVo.class);
        List<StringToJsonVo> stringToJsonVos = JSONArray.parseArray(s2, StringToJsonVo.class);
        System.out.println(stringToJsonVos);

        //jsonArray转List<pojo>
        JSONArray array = JSONArray.parseArray(s2);
        List<StringToJsonVo> stringToJsonVos1 = JSONArray.parseArray(array.toJSONString(), StringToJsonVo.class);

        String s3 = s();
        System.out.println(s3);
       // List<StringToJsonVo> js3 = JSONArray.parseArray(s3, StringToJsonVo.class);
       // System.out.println(js3);



    }
    public String s(){
        List<StringToJsonVo> list = new ArrayList<>();
        StringToJsonVo stringToJsonVo = new StringToJsonVo();
        stringToJsonVo.setId("1");
        stringToJsonVo.setText("nihao");
        list.add(stringToJsonVo);
        System.out.println("===>"+list);
     //   JSONArray objects = JSONArray.parseArray(list.toString());
        return list.toString();
    }
}
