<?php


//定义连接数据库函数
$conn = mysqli_connect("mysql648.db.sakura.ne.jp", "restaurant", "hay113718", "restaurant_113");
function conn(){
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
} 
}
//注册
if(isset($_POST["send"])) {
$dbName=$_POST['userName'];
$dbPhone=$_POST['userPhone'];
$dbEmail=$_POST['userEmail'];
$dbPass=$_POST['userPass'];
$dbaddress=$_POST['address'];
conn();
$i=mysqli_query($conn, "select user from users where user = '$dbName'");
$r = mysqli_fetch_assoc($i);
if ($r==null) {
     mysqli_query($conn, "insert into users (user, pass,email,tel,address) values ('$dbName','$dbPass','$dbEmail','$dbPhone','$dbaddress')");
       $url = "http://restaurant.sakura.ne.jp/外卖下单/index.html";
       echo "<script language='javascript' type='text/javascript'>";
       echo "window.location.href='$url'";
       echo "</script>";
 }  else {
           echo "<script> alert('用户名已被注册请重新注册');parent.location.href='http://restaurant.sakura.ne.jp/外卖下单/registeration.html'; </script>";
 }
}

//登录
if(isset($_POST["login"])) {
$loginuser=$_POST['username'];
$loginpass=$_POST['password'];
conn();
$s = mysqli_query($conn, "select user and pass from users where user = '$loginuser' and pass ='$loginpass'");
$row = mysqli_fetch_assoc($s);
if($row != null){
       $url = "http://restaurant.sakura.ne.jp/外卖下单/index.html";
       echo "<script language='javascript' type='text/javascript'>";
       echo "window.location.href='$url'";
       echo "</script>";
}else{
           echo "<script> alert('账号或者密码有误返回重新登录');parent.location.href='http://restaurant.sakura.ne.jp/外卖下单/login.html'; </script>";

           // echo "<script>alert('登录失败')</script>";
}
         // header('location:http://www.restaurant.sakura.ne.jp/外卖下单/login.html');










}








?>