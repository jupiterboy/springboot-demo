package com.tcb.config;


import com.tcb.config.msg.NodeMessage;
import com.tcb.sts.station.msg.Collector;
import com.tcb.utils.ZipCompress;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws Exception {
//        File file = new File("E:\\snipher\\data\\tcb\\M0000002\\report\\ch21\\value\\2022\\03\\20220308160348.data");
//        File file = new File("E:\\snipher\\data\\tcb\\M0000002\\report\\ch14\\value\\2022\\03\\20220308160348.data");
        File file = new File("E:\\snipher\\data\\tcb\\M0000002\\report\\ch32\\value\\2022\\03\\20220308160348.data");
        byte[] data = FileCopyUtils.copyToByteArray(file);
        data = Arrays.copyOfRange(data, 6, data.length);

        long start = System.currentTimeMillis();
        for(int i=0; i< 1000; i++){
            NodeMessage.Message msg = NodeMessage.Message.parseFrom(data);
//            System.out.println(msg);
            Collector.CollectorMessage cmsg = Collector.CollectorMessage.parseFrom(ZipCompress.decompress(msg.getPayload().getData().toByteArray()));
//            System.out.println(cmsg);
        }

        System.out.println(System.currentTimeMillis() - start);

    }
}
