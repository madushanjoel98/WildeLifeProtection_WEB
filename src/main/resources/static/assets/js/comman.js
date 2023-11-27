
    //var currentUrl = window.location.href;
    // Get the pathname (path part of the URL)
    var pathname = window.location.pathname;
    // Split the pathname to get individual segments
    var pathSegments = pathname.split('/');
    // The context path is the first segment after the domain
    var contextPath = '/' + pathSegments[1];

    function showSubmissionNotification(messageType, messageBody){	
      if(messageType=='error'){
        swal({title: 'Warning!',text:messageBody,type: messageType,confirmButtonColor: '#4fa7f3'});
      }
      if(messageType=='success'){
        swal({title: 'Success!',text:messageBody,type:messageType,confirmButtonColor: '#4fa7f3'});
      }
    }