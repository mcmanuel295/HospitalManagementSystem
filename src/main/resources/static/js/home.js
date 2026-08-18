const modal = document.querySelector("#login-modal");
const loginButtons = document.querySelectorAll("[data-open-login]");
const closeButton = document.querySelector("[data-close-login]");
const loginForm = document.querySelector("#login-form");
const formMessage = document.querySelector("#form-message");
const menuToggle = document.querySelector(".menu-toggle");
const navLinks = document.querySelector(".nav-links");

function setModal(open) {
    modal.classList.toggle("is-open", open);
    modal.setAttribute("aria-hidden", String(!open));
    if (open) modal.querySelector("input").focus();
}

loginButtons.forEach((button) => button.addEventListener("click", () => setModal(true)));
closeButton.addEventListener("click", () => setModal(false));
modal.addEventListener("click", (event) => {
    if (event.target === modal) setModal(false);
});
document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") setModal(false);
});

menuToggle.addEventListener("click", () => {
    const open = navLinks.classList.toggle("is-open");
    menuToggle.setAttribute("aria-expanded", String(open));
});
navLinks.querySelectorAll("a").forEach((link) => link.addEventListener("click", () => navLinks.classList.remove("is-open")));

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const formData = new FormData(loginForm);
    const submitButton = loginForm.querySelector("button");
    submitButton.disabled = true;
    submitButton.textContent = "Signing in...";
    formMessage.textContent = "";

    try {
        const response = await fetch("/api/v1/users/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: formData.get("email"), password: formData.get("password") })
        });
        if (!response.ok) throw new Error("Invalid credentials");
        const token = await response.text();
        localStorage.setItem("hospitalAccessToken", token);
        formMessage.style.color = "#4e9a68";
        formMessage.textContent = "Signed in. Your care workspace is ready.";
    } catch (error) {
        formMessage.style.color = "#ed7d62";
        formMessage.textContent = "We could not sign you in. Check your details and try again.";
    } finally {
        submitButton.disabled = false;
        submitButton.textContent = "Continue to workspace";
    }
});

document.querySelector("#year").textContent = new Date().getFullYear();
