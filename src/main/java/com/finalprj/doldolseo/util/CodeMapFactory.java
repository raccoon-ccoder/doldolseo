package com.finalprj.doldolseo.util;

import java.util.HashMap;
import java.util.Map;

/*
 * 지역및 컨텐츠타입 코드-이름 매핑 유틸
 */
public class CodeMapFactory {

    private Map<Integer, String> areaMap;
    private Map<Integer, String> contentTypeMap;

    public Map<Integer, String> getAreaMap() {

        this.areaMap = new HashMap<>();

        this.areaMap.put(1, "강남");
        this.areaMap.put(2, "강북");
        this.areaMap.put(3, "광화문");
        this. areaMap.put(4, "명동");
        this.areaMap.put(5, "여의도");
        this.areaMap.put(6, "잠실");
        this.areaMap.put(7, "홍대");
        this.areaMap.put(0, "etc");
        this.areaMap.put(null, "전체");

        return this.areaMap;
    }

    public Map<Integer, String> getContentTypeMap() {
        this.contentTypeMap = new HashMap<>();

        this.contentTypeMap.put(1, "축제&행사");
        this.contentTypeMap.put(2, "음식");
        this.contentTypeMap.put(3, "쇼핑");
        this.contentTypeMap.put(4, "문화&관광");
        this.contentTypeMap.put(0, "etc");
        this.contentTypeMap.put(null, "전체");

        return this.contentTypeMap;
    }

}
