function getCallbackUrl() {
  let callbackUrl = '';
  try {
    callbackUrl = window.location.search
      .substring(1)
      .split("&")
      .filter(pair => pair.indexOf("callbackUrl=") > -1)[0]
      .split("=")[1];
  } catch (e) {
  }
  return callbackUrl;
}