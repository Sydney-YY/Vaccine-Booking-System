function search_box() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search-bar");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[0];
      if (td) {
        txtValue = td.textContent || td.innerText;

            //    .includes();   // this is case sensitive
          if(txtValue.replace(/\s+/g,'').toUpperCase().includes(filter)){
              tr[i].style.display="";
          }else{
              tr[i].style.display = "none";
          }

        // if (txtValue.toUpperCase().indexOf(filter) > -1) {
        //   tr[i].style.display = "";
        // } else {
        //   tr[i].style.display = "none";
        // }
      }
    }
  }