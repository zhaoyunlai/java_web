对于FruitDAOImpl中获取连接操作以及释放资源操作做了提取

抽取增删改操作
可以看到下面这个三个操作的步骤完全一样，不一样的地方只是SQL语句和psmt设置的参数
因此可以将这些相同的操作抽取出来

addFruit:
1.获取连接
2.编写SQL
3.psmt
4.执行更新
5.关闭资源

updateFruit：
1.获取连接
2.编写SQL
3.psmt
4.执行更新
5.关闭资源

deleteFruit
1.获取连接
2.编写SQL
3.psmt
4.执行更新
5.关闭连接