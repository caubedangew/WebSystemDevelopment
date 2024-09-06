<%-- 
    Document   : checkout
    Created on : Aug 29, 2024, 8:20:54 AM
    Author     : ThienVu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action='/charge' method='POST' id='checkout-form'>
    <input type='hidden' th:value='${amount}' name='amount' />
    <label>Price:<span th:text='${amount/100}' /></label>
    <!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
    <script
       src='https://checkout.stripe.com/checkout.js' 
       class='stripe-button'
       th:attr='data-key=${stripePublicKey}, 
         data-amount=${amount}, 
         data-currency=${currency}'
       data-name='Baeldung'
       data-description='Spring course checkout'
       data-image
         ='https://www.baeldung.com/wp-content/themes/baeldung/favicon/android-chrome-192x192.png'
       data-locale='auto'
       data-zip-code='false'>
   </script>
</form>
