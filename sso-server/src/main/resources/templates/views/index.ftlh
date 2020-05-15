<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>SSO 统一认证中心</title>

    <#-- js   -->
    <script src="https://unpkg.com/axios@0.19.2/dist/axios.js"></script>
    <script src="https://unpkg.com/js-cookie@2.2.1/src/js.cookie.js"></script>
    <script src="/static/js/utils.js"></script>
</head>
<body>

</body>
<script>
  function validTGC(TGC) {
    return axios({
      method: 'get',
      url: '/sso/valid_TGC?TGC=' + TGC
    }).then((res) => {
      const {code} = res.data;
      return code - 0 === 200;
    }).catch(() => {
      return false;
    })
  }

  function generateST(TGC) {
    return axios({
      method: 'get',
      url: '/sso/generate_ST?TGC=' + TGC
    }).then((res) => {
      const {data} = res.data;
      if (data && data['ST']) {
        // 将 Ticket 传给前端
        return data['ST']
      }
      return null
    }).catch(() => {
      return null
    })
  }

  function main() {
    let callbackUrl = getCallbackUrl();
    if (!callbackUrl) {
      alert('回调地址获取失败，请返回原页面重试')
      return;
    }
    let TGC = Cookies.get('TGC')
    if (!TGC) { // 没有 cookie
      window.location.href = '/sso/login?callbackUrl=' + callbackUrl
      return;
    }
    validTGC(TGC).then(valid => {
      if (!valid) { // cookie 无效
        window.location.href = '/sso/login?callbackUrl=' + callbackUrl
        return;
      }
      // cookie 有效，表示曾经在 SSO 登录过，此时重新生成 ST
      generateST(TGC).then(ST => {
        if (ST) {
          window.location.href = callbackUrl + "?ST=" + ST;
        }
      })
    })
  }

  main()
</script>


</html>