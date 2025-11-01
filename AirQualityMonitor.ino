#ifdef ESP32
  #include <WiFi.h>
  #include <HTTPClient.h>
  
#else
  #include <ESP8266WiFi.h>
  #include <ESP8266HTTPClient.h>
  #include <WiFiClient.h>
#endif
const char* ssid = "OPPO A53";
const char* pwd = "vidya2003";
WiFiServer server(80);  // open port 80 for server connection

int mq3 = 34;
int mq7 = 35;
int relay = 13;
int val1, val2;
void setup() {
    pinMode(mq3, INPUT);
    pinMode(mq7, INPUT);
    pinMode(relay, OUTPUT);
    WiFi.begin(ssid, pwd);   
    server.begin();
    Serial.begin(9600);
}

void loop() {
val1 = analogRead(mq3);
val2 = analogRead(mq7);
Serial.print("MQ3 Range: ");
Serial.println(val1);
Serial.print("MQ7 Range: ");
Serial.println(val2);
delay(1000);
if(val1> 1900 || val2 > 900)
{
  digitalWrite(relay, LOW);
  
  }
  else  
  {
      digitalWrite(relay, HIGH);
    }
      get_device_status();
      get_device_status1();
      

}
void get_device_status()
{
        
       WiFiClient client = server.available();
       HTTPClient http;
       String url = "https://api.thingspeak.com/update?api_key=BYP6S2U44HVWE1K1&field1=" + String(val2);
       http.begin(url);
       int httpCode = http.POST(url);
       http.end();
       Serial.println(url);
}
void get_device_status1()
{
        
       WiFiClient client = server.available();
       HTTPClient http;
       String url = "https://api.thingspeak.com/update?api_key=BYP6S2U44HVWE1K1&field2=" + String(val1);
       http.begin(url);
       int httpCode = http.POST(url);
       http.end();
       Serial.println(url);
}
