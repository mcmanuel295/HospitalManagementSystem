// Check if user is logged in
function checkAuth() {
  const token = localStorage.getItem('token');
  if (!token && !window.location.pathname.includes('index.html')) {
    window.location.href = '/../index.html';
  }
  if (token) {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    if (document.getElementById('userDisplay')) {
      document.getElementById('userDisplay').textContent = `Welcome, ${user.name || 'User'}`;
    }
  }
}

function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  window.location.href = '/../index.html';
}

checkAuth();
