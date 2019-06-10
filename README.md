# spring-boot-elk
Download zip version of Elastic-Search, Logstash and Kibana.
Run the elasticsearch.bat(from path elasticsearch-7.1.1\bin) and then access it at http://localhost:9200

Modify the kibana.yml(at path kibana-7.1.1\config) to point to the elasticsearch instance. In our case this will be 9200. So uncomment the following line in kibana.yml-
elasticsearch.hosts: ["http://localhost:9200"]
server.port: 5601

Run the kibana.bat(from path kibana-7.1.1\bin) and access kibana UI at http://localhost:5601

Mention the location of the log file to be created in the application.properties file of spring-boot application using following property. With this property, application logs will be created at mentioned path:
logging.file=C:/D_Drive/Projects/Logs/spring-boot-elk.log

Now configure the logstash pipeline. This is done using the logstash.conf file. Copy logstash.conf to bin folder of Logstash software, for example copy to following path:
C:\D_Drive\SW\ELK\logstash-7.1.1\bin

Start logstash using the command prompt by going to path: [C:\D_Drive\SW\ELK\logstash-7.1.1\bin]
logstash -f logstash.conf

Start the spring boot application. Logs will be generated in "C:/D_Drive/Projects/Logs" folder.
Now access the valid springboot service URLs(as mentiond in @RestController java file)
goto localhost:8080/elk
goto localhost:8080/exception

After accessing above services, to see what indices have been created, go to following URL:
http://localhost:9200/_cat/indices/?v
Example Output:
health status index                      uuid                   pri rep docs.count docs.deleted store.size pri.store.size
green  open   .kibana_1                  GZnqCfgASaaQ3L2rE5_dWg   1   0          4            1       23kb           23kb
yellow open   logstash-2019.06.10-000001 NoL2FlzIQ7KA7HNfSLCxnw   1   1         22            0       66kb           66kb
green  open   .kibana_task_manager       8IwBQT91SMyNo2mGPE0tXQ   1   0          2            0     45.5kb         45.5kb

To see what data has been indexed at Kibana for above mentioned logstash index, go to following URL having above index:
http://localhost:9200/<INDEX_NAME>/_search
Example:
http://localhost:9200/logstash-2019.06.10-000001/_search

To see logs in Kibana UI, go to kibana UI console --> Management --> Index Patterns --> Create index pattern --> mention index pattern as logstash-* to see the indexed data.
After index pattern is created, go to "Discover". To filter, select time duration (like Today/or last 15 minutes) and see the logs.
