const Utils = {
    showNotification: function(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = `alert alert-${type}`;
        notification.textContent = message;
        notification.style.position = 'fixed';
        notification.style.top = '20px';
        notification.style.right = '20px';
        notification.style.zIndex = '9999';
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 5000);
    },

    showLoading: function() {
        const spinner = document.getElementById('loadingSpinner');
        if (spinner) spinner.style.display = 'flex';
    },

    hideLoading: function() {
        const spinner = document.getElementById('loadingSpinner');
        if (spinner) spinner.style.display = 'none';
    },
};
