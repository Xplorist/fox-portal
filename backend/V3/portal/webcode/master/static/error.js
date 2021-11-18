(function() {
  window.onerror = function(message, source, lineno, colno, error) {
    if (message.includes('qiankun')) {
      return true;
    }
  };
  window.addEventListener('error', function(error) {
    if (error.message.includes('qiankun')) {
      return true;
    }
  }, true);
})()