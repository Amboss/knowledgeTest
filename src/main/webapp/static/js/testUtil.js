   <script type="text/javascript">
       /* Timer to reload task in 3 minutes */
       function MyTimer()
       {
          var valueTimer = $('#timer').val();

          if(valueTimer > 0)
          {
              valueTimer = valueTimer - 1;

              hours = (valueTimer/3600).toString().split('.')[0];
              mins  = ((valueTimer % 3600) / 60).toString().split('.')[0];
              secs  = ((valueTimer % 3600) % 60).toString();

              if(hours.length == 1) hours = '0' + hours;
              if(mins.length  == 1) mins  = '0' + mins;
              if(secs.length  == 1) secs  = '0' + secs;

              $('#idTimerLCD').text( hours + ':' +  mins + ':'  + secs);
              $('#hdnTimer').val( valueTimer );
          }
          else
          {
              $('#btnNext').attr('disabled', 'disabled');
              alert(" Your time is up ! \n\n Let's see the Result & Statistics of the Test.");
              PopulateResultStatics();
          }
       }
   </script>