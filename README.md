# gfg-android-assignment

This android assignment is following MVVM architecture and developed in Kotlin. I've used Retrofit and RxJava for the network call. I implemented the logic for showing 2 different layouts with a single recyclerview. <br/>
In order to match the design, I added PTSansCaption-Regular.ttf font and color '#5c845d' <br/>

Implemented Pull to refresh in order to load the articles. 
Also, Formatting the date (yyy-mm-dd hh:mm:ss): e.g. Mar 24, 2021 09:28 AM <br/>

Loading images:<br/>
  For Larger article, showing image from items/enclosure/link. <br/>
  For Regular articles, showing image from items/thumbnail. <br/>
  
  
Image path is showing blank image from API response. I added logs in FeedAdapter.kt file for better understanding. <br/><br/>

Attaching the screenshots. One is using placeholder image and other one is using image path that is coming from API (rendering image using Glide).
<br/>

<img align="left" src="https://user-images.githubusercontent.com/20974986/112303463-51de4f80-8cc2-11eb-8a2b-39109c4b2305.jpg" alt="Screenshot" width="350" height="700" />

<img align="right" src="https://user-images.githubusercontent.com/20974986/112303516-5f93d500-8cc2-11eb-8135-02d8c694b4e6.jpg" alt="Screenshot" width="350" height="700" />


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

Listing some image urls which is coming from API <br/>

https://live-production.wcms.abc-cdn.net.au/a4a58ac67f38ecdfeeafb10b941e5b17?impolicy=wcms_crop_resize&amp;cropH=383&amp;cropW=287&amp;xPos=181&amp;yPos=0&amp;width=862&amp;height=1149

https://live-production.wcms.abc-cdn.net.au/39acafbd0db9001687a90d71fd8bcef0?impolicy=wcms_crop_resize&amp;cropH=1987&amp;cropW=1490&amp;xPos=755&amp;yPos=0&amp;width=862&amp;height=1149

https://live-production.wcms.abc-cdn.net.au/de0b3c48b7a9fce1f1cbd70e6bd58d3a?impolicy=wcms_crop_resize&amp;cropH=2679&amp;cropW=2008&amp;xPos=0&amp;yPos=315&amp;width=862&amp;height=1149







