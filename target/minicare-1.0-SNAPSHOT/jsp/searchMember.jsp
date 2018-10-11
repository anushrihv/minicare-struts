<style>
 input[type=submit] {
    background-color: #555555; /*black*/
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 12px;
}
</style>

<html>
    <body>
        <h3 align="center">ENTER EMAIL TO SEARCH MEMBER </h1>
        <div align="center">
        <form action="/minicare-1.0-SNAPSHOT/member/searchmember.do">
            <input type="text" name="memberemail">
            <input type="submit" value="SEARCH">
        </form>
        </div>
    </body>
</html>