# spring-boot-elk
Download zip version of Elastic-Search, Logstash and Kibana.<br/>
Run the elasticsearch.bat(from path elasticsearch-7.1.1\bin) and then access it at http://localhost:9200 <br/>

Modify the kibana.yml(at path kibana-7.1.1\config) to point to the elasticsearch instance. In our case this will be 9200. So uncomment the following line in kibana.yml: <br/>
elasticsearch.hosts: ["http://localhost:9200"] <br/>
server.port: 5601 <br/>

Run the kibana.bat(from path kibana-7.1.1\bin) and access kibana UI at http://localhost:5601 <br/>

Mention the location of the log file to be created in the application.properties file of spring-boot application using following property. With this property, application logs will be created at mentioned path: <br/>
logging.file=C:/D_Drive/Projects/Logs/spring-boot-elk.log <br/>

Now configure the logstash pipeline. This is done using the logstash.conf file. Copy logstash.conf to bin folder of Logstash software, for example copy to following path: <br/>
C:\D_Drive\SW\ELK\logstash-7.1.1\bin <br/>

Start logstash using the command prompt by going to path: [C:\D_Drive\SW\ELK\logstash-7.1.1\bin] <br/>
logstash -f logstash.conf <br/>

Start the spring boot application. Logs will be generated in "C:/D_Drive/Projects/Logs" folder. Now access the valid springboot service URLs(as mentiond in @RestController java file) <br/>
goto localhost:8080/elk <br/>
goto localhost:8080/exception <br/>

After accessing above services, to see what indices have been created, go to following URL: <br/>
http://localhost:9200/_cat/indices/?v <br/>
Example Output: <br/>
index                     
.kibana_1 <br/>
logstash-2019.06.10-000001 <br/>
.kibana_task_manager <br/>

To see what data has been indexed at Kibana for above mentioned logstash index, go to following URL having above index: <br/>
http://localhost:9200/<INDEX_NAME>/_search <br/>
Example: <br/>
http://localhost:9200/logstash-2019.06.10-000001/_search <br/>

To see logs in Kibana UI, go to kibana UI console --> Management --> Index Patterns --> Create index pattern --> mention index pattern as logstash-* to see the indexed data. <br/>
After index pattern is created, go to "Discover". To filter, select time duration (like Today/or last 15 minutes) and see the logs.
