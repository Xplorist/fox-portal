(function() {
  var progress = document.createElement('div');
  progress.id = 'master-top-progress';
  progress.style.width = '0';
  progress.style.height = '2px';
  progress.style.background = '#3A7EFF';
  progress.style.transition = 'width 2s';
  document.body.insertBefore(progress, document.getElementById('root-master'));
  setTimeout(function() {
    progress.style.width = '50%';
  }, 200);
})();
