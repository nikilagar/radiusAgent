# Privacy Policy for AI Resume Tailor

**Last Updated:** [Date]

## 1. Overview
AI Resume Tailor ("we", "our", or "us") respects your privacy. This policy explains how we handle your data. **In short: Your data stays on your device or your own private server.**

## 2. Data We Collect
We do **not** collect, store, or transmit your personal data to our own servers. The extension processes data as follows:

-   **Resume Data**: Your master resume and generated resumes are stored locally in your browser's storage (`chrome.storage.local`).
-   **Job Descriptions**: We temporarily read the content of the active tab to extract job descriptions for tailoring purposes.
-   **Form Data**: We may read form fields on job application pages to enable the "Smart Fill" feature.

## 3. Data Transmission
-   **Self-Hosted Processing**: All AI processing (resume tailoring, PDF generation) happens on **your own self-hosted backend**. The extension communicates exclusively with the API URL you configure (e.g., your AWS EC2 instance).
-   **No Third-Party Sharing**: We do not send your data to any third-party analytics, tracking services, or external cloud providers.

## 4. Permissions Usage
-   **ActiveTab**: Used to read the job description from the current page when you click the extension or trigger an analysis.
-   **Storage**: Used to save your resume settings and history locally on your device.
-   **Scripting**: Used to auto-fill job application forms based on your tailored resume.

## 5. Your Choices
You have full control over your data. You can:
-   Delete your stored resume data at any time via the extension settings.
-   Configure the extension to point to any server of your choice.

## 6. Contact
If you have questions about this policy, please contact us at [Your Email].
