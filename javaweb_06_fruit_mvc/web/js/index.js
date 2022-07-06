function delFruit(fid) {
    if(confirm('是否确认删除？')){
        // window.location是获取窗口，href获取url
        window.location.href='del.do?fid='+fid
    }
}
function page(pageNo) {
    window.location.href='fruit_index?pageNo='+pageNo
}