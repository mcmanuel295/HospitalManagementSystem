// Authentication handler
document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('loginForm');
  if (form) {
    form.addEventListener('submit', async (e) => {
      e.preventDefault();
      const username = document.getElementById('username').value;
      const password = document.getElementById('password').value;
      
      try {
        // Mock authentication - replace with real API call
        if (username && password) {
          localStorage.setItem('token', 'mock-jwt-token');
          localStorage.setItem('user', JSON.stringify({ name: username }));
          window.location.href = 'pages/dashboard.html';
        }
      } catch (error) {
        document.getElementById('errorMsg').textContent = 'Invalid credentials';
        document.getElementById('errorMsg').style.display = 'block';
      }
    });
  }
});
