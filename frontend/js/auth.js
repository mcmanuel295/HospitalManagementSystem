const Auth = {
    login: async function(username, password) {
        try {
            Utils.showLoading();
            const response = await fetch(`${CONFIG.API_BASE_URL}/users/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password }),
            });
            const data = await response.json();
            if (!response.ok) throw new Error(data.message || 'Login failed');
            localStorage.setItem(CONFIG.STORAGE_KEYS.TOKEN, data.token);
            localStorage.setItem(CONFIG.STORAGE_KEYS.USER, JSON.stringify(data.user));
            Utils.hideLoading();
            Utils.showNotification('Login successful!', 'success');
            setTimeout(() => { window.location.href = 'pages/dashboard.html'; }, 500);
        } catch (error) {
            Utils.hideLoading();
            Utils.showNotification(error.message, 'error');
        }
    },
};

if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', (e) => {
        e.preventDefault();
        Auth.login(document.getElementById('username').value, document.getElementById('password').value);
    });
}
