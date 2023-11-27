
function baseClients_form(url, method, data, callback) {
    var jsonData = JSON.stringify(data);
    // Send the JSON data to the API
    $.ajax({
      url: url,
      type: method,
      contentType: 'application/json',
      data: jsonData,
      success: function(response) {
        // Handle the successful response from the API
        console.log(response);
        callback(response, null); // Pass the response to the callback
      },
      error: function(xhr, status, error) {
        console.log(error);
        callback(null, error); // Pass the error to the callback
      }
    });
  }
  
  // Usage example:
//   var formData = {
//     // Your form data here
//   };
  
//   baseClients_form('your_api_endpoint', 'POST', formData, function(response, error) {
//     if (response) {
//       // Handle the response
//       console.log(response);
//     } else {
//       // Handle the error
//       console.log(error);
//     }
//   });