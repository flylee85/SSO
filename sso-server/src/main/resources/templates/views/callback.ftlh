<h3> demo 页面，实际应为业务方回调页面</h3>
<div id="user"></div>

<script src="https://unpkg.com/axios@0.19.2/dist/axios.js"></script>

<script>
  function getTicket() {
    let ticket = '';
    try {
      ticket = window.location.search.substring(1).split('&')[0].split('=')[1];
    } catch (e) {
    }
    return ticket
  }

  function getUserByST(ST) {
    return axios({
      method: 'get',
      url: '/sso/business_client_demo?ST=' + ST
    }).then((res) => {
      const {data} = res.data;
      if (data) {
        document.getElementById("user").innerText = "用户:" + data.name
        console.log(data)
      }
      return data
    }).catch(() => {
      return null
    })
  }

  function main() {
    let ticket = getTicket();
    if (!ticket) return;
    // 传给业务方自己的服务，获取用户信息
    getUserByST(ticket);
  }

  main()
</script>

