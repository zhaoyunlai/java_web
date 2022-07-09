

1. 最初的做法是：一个请求对应一个Servlet，这样存在的问题是servlet太多了
2. 把一些列的请求都对应一个servlet，IndexServlet、AddServlet、EditServlet等
   通过一个operate的值来确定来决定调用FruitServlet中的哪个方法
   使用的是switch-case方法。对应 项目06
