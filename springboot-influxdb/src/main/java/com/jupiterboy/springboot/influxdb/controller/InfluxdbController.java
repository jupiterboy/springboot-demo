package com.jupiterboy.springboot.influxdb.controller;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.jupiterboy.springboot.influxdb.Temperature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@RestController
public class InfluxdbController {

    private static char[] token = "uO47RrV_med_pJaedtnYluHz4W2Pn-N2uR9_bSKhyx1JEFk4q8BFg8_3TmobPjZooYMozcFXf9y0PDqfPqec1A==".toCharArray();
    private static String org = "my-org";
    private static String bucket = "my-bucket";

    private Random random = new Random();

    @RequestMapping("writePoint1")
    public void insertPoint1(){
        try{
            InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

            Point point = Point.measurement("temperature")
                    .addTag("location", "west")
                    .addField("value", random.nextDouble()*100)
                    .time(Instant.now().toEpochMilli(), WritePrecision.MS);

            writeApi.writePoint(point);

            influxDBClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("writePoint2")
    public void insertPoint2(){
        try{
            InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

            //
            // Write by POJO
            //
            Temperature temperature = new Temperature();
            temperature.location = "south";
            temperature.value = random.nextDouble()*100;
            temperature.time = Instant.now();

            writeApi.writeMeasurement(WritePrecision.NS, temperature);

            influxDBClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("writePoint3")
    public void insertPoint3(){
        try{
            InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

            //
            // Write by POJO
            //
            Temperature temperature = new Temperature();
            temperature.location = "south";
            temperature.value = random.nextDouble()*100;
            temperature.time = Instant.now();

            writeApi.writeMeasurement( WritePrecision.NS, temperature);

            influxDBClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("queryPoint")
    public void queryPoint(){
        try{
            InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);
            WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

            //
            // Query data
            //
            String flux = "from(bucket:\"my-bucket\") |> range(start: 0)";

            QueryApi queryApi = influxDBClient.getQueryApi();

            List<FluxTable> tables = queryApi.query(flux);
            for (FluxTable fluxTable : tables) {
                List<FluxRecord> records = fluxTable.getRecords();
                for (FluxRecord fluxRecord : records) {
                    System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_value"));
                }
            }

            influxDBClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
