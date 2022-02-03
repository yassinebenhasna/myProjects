var REQUEST_TIMEOUT = 10000;
$(document).ready(function(){
    $(".copyCode").on("click", function(){
        var str = this.parentNode.textContent;
        const el = document.createElement('textarea');  // Create a <textarea> element
          el.value = str;                                 // Set its value to the string that you want copied
          el.setAttribute('readonly', '');                // Make it readonly to be tamper-proof
          el.style.position = 'absolute';                 
          el.style.left = '-9999px';                      // Move outside the screen to make it invisible
          document.body.appendChild(el);                  // Append the <textarea> element to the HTML document
          const selected =            
            document.getSelection().rangeCount > 0        // Check if there is any content selected previously
              ? document.getSelection().getRangeAt(0)     // Store selection if found
              : false;                                    // Mark as false to know no selection existed before
          el.select();                                    // Select the <textarea> content
          document.execCommand('copy');                   // Copy - only works as a result of a user action (e.g. click events)
          document.body.removeChild(el);                  // Remove the <textarea> element
          if (selected) {                                 // If a selection existed before copying
            document.getSelection().removeAllRanges();    // Unselect everything on the HTML document
            document.getSelection().addRange(selected);   // Restore the original selection
          }
          document.execCommand("copy");
    });
});


function callFarasa(text, task){
  data = {"text" : text, "task" : task, "API_KEY": "lpcsTkDIDf"};
  $('#parse-images').html("");
  $('#output').html("");

  var poster = $.post("analyze/", data);
    poster.done (function(data){
      data = JSON.parse(data);
      console.log(task);
      if (task === "spellcheck"){
        var tokens = data["text"].split(" ");
                tokens.forEach((value, index, array) => {
                    if (value.includes('/')) {
                        let parts = value.split('/');

                        array[index] = `<span style="color:red;"><u>${parts[0]}</u></span>`
                    }
                    else {
                        array[index] = value;
                    }
                });
                
                $( "#analyzedText" ).empty().append(tokens.join(" "));
                $("#analyzedText").css("background-color", "#ffffff");


      } else{
        console.log("YOO", data);
        $("#analyzedText").html(data["text"]);
      }

    });
}

function closeModal(){

  console.log("closing modal");
  var modal = document.getElementById("myModal");

  var span = document.getElementsByClassName("close")[0];
  modal.style.display = "none";
            

}


// function openModal(){

//   console.log("closing modal");
//   var modal = document.getElementById("myModal");

//   var span = document.getElementsByClassName("close")[0];
//   modal.style.display = "none";
            

// }

function changeImage(imgpath) {
            console.log("hi hi");
            var modal = document.getElementById("myModal");
            var modalImg = document.getElementById("img01");
            var captionText = document.getElementById("caption");
            modal.style.display = "block";
            modalImg.src = imgpath;
zz
            // Get the <span> element that closes the modal

            // When the user clicks on <span> (x), close the modal
            


            // var img = document.getElementById("modal-img");
            // img.src = imgpath;
            // return false;
        }

function callQatsdemo(task, query, normalized){
        $('#analyzedText').html("");
        console.log("calling qats")
        $.ajax({
        type: 'POST',
        url: 'callQats/',
        data: { query: query , task: task , normalized: normalized },
        timeout: REQUEST_TIMEOUT,
        success:function(data){
          // successful request; do something with the data
          data = data.replace("UNDERSCORE" , "_");
          if(task == 3){
            disooqi = data.split("<disooqi>");
            data = disooqi[0];
                //var imgs = disooqi[1];
            // console.log(data);  
            // console.log(disooqi[1]);
          // var s = { html: disooqi[1] };
          // var d = JSON.stringify(s);
          // console.log(d);

          $('#parse-images').html(disooqi[1]);

          var parsed = document.getElementById("parse-images").children;
          for (var i = 0; i < parsed.length; i++){
            var tree = "parse-tree-" + (i+1).toString();
            // console.log(tree);
            tree = document.getElementById(tree);
            var treeImg = tree.children[0];
            var len = "http://localhost:8000/dev/".length;
            treeImg.src = "http://qatsdemo.cloudapp.net/farasa/" + treeImg.src.substring(len, treeImg.src.length);


            var modal = document.getElementById("myModal");
            var modalImg = document.getElementById("img01");
            var captionText = document.getElementById("caption");

            treeImg.onclick = function(){
              modal.style.display = "block";
              modalImg.src = this.src;
              captionText.innerHTML = this.alt;
            }



            tree.children[1].children[2].children[1].href = treeImg.src;
            tree.children[1].children[2].children[1].target="_blank";
            tree.children[1].children[2].children[0].onclick = function() { changeImage(treeImg.src); }
;            
          }


        }else{$('#parse-images').html("");}
          // console.log(data);
          // $('#output').html(data);
          console.log("outputting");
          console.log("data", data);
          $('#output').html(data);

        }

        // error:function(error){
        //   // failed request; give feedback to user
        //   // $('#output').text(error.responseText + "\n" + error.statusText );
        //   $('#output').text("ERROR processing text" );
        // },
        // complete:function(){
        //   $('#loadingImg').css('visibility' , 'hidden');
        //   $('#output').css('visibility' , 'visible');
        // }
      });
}

function analyze(){
	console.log("Hello World");
	var text = $("#raw-text").val();
	var task = $("#module").val();
  console.log("task,", task);
  if (task === "dependencyParser"){
    task = 3;
    callQatsdemo(task,text,true);
  } else if (task === "NER"){
    task = 5;
    console.log("Going qats");

    callQatsdemo(task,text,true);
  } else if (task === "constituencyParser"){
    task = 6;
    callQatsdemo(task,text,true);

  } else {
    callFarasa(text, task);
  }	
}

function analyzeText(task){
  if (task === "NER"){
    task = 5;
    console.log("Going qats");
    var text = $("#raw-text").val();

    callQatsdemo(task,text,true);
  } else{
    console.log("Hello World");
    var text = $("#raw-text").val();
    callFarasa(text, task);
  }

}