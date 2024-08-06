const stripe = Stripe('pk_test_51OD6R9A1DOgg5N726qPLKgF7rrsLdzEZXO4XTOip7yh1MI9aMgiBklj8ZWLbyrJ6wOTKl3hvpnykIiXzk3ftBqE100b3I1S6u1');
 const paymentButton = document.querySelector('#paymentButton');

 paymentButton.addEventListener('click', () => {
	 console.log('Button clicked!');
   stripe.redirectToCheckout({
     sessionId: sessionId
   })
 });