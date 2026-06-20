import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import api from '../api';

function AiToolsPage() {
  const [resumeText, setResumeText] = useState('');
  const [codeSnippet, setCodeSnippet] = useState('');
  const [targetRole, setTargetRole] = useState('Java Developer');
  const [skills, setSkills] = useState('Spring Boot, REST APIs, SQL');
  const [result, setResult] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const submitResume = async () => {
    try {
      const response = await api.post('/ai/resume-review', { resumeText });
      setResult(response.data.result);
    } catch (err) {
      setError('Resume review failed.');
    }
  };

  const submitCode = async () => {
    try {
      const response = await api.post('/ai/code-review', { codeSnippet });
      setResult(response.data.result);
    } catch (err) {
      setError('Code review failed.');
    }
  };

  const submitInterview = async () => {
    try {
      const response = await api.post('/ai/interview-questions', { targetRole, skills });
      setResult(response.data.result);
    } catch (err) {
      setError('Interview prep failed.');
    }
  };

  return (
    <div className="panel">
      <header className="page-header">
        <div>
          <h1>AI Workspace</h1>
          <p>Generate smarter content for hiring and technical prep.</p>
        </div>
        <div>
          <button onClick={() => navigate('/dashboard')}>Back</button>
        </div>
      </header>

      <section className="tool-card">
        <h2>Resume Review</h2>
        <textarea value={resumeText} onChange={(e) => setResumeText(e.target.value)} placeholder="Paste your resume summary here" />
        <button onClick={submitResume}>Run Resume Review</button>
      </section>

      <section className="tool-card">
        <h2>Code Review</h2>
        <textarea value={codeSnippet} onChange={(e) => setCodeSnippet(e.target.value)} placeholder="Paste a Java or backend snippet" />
        <button onClick={submitCode}>Run Code Review</button>
      </section>

      <section className="tool-card">
        <h2>Interview Prep</h2>
        <label>
          Target role
          <input value={targetRole} onChange={(e) => setTargetRole(e.target.value)} />
        </label>
        <label>
          Key skills
          <input value={skills} onChange={(e) => setSkills(e.target.value)} />
        </label>
        <button onClick={submitInterview}>Generate Questions</button>
      </section>

      {error && <div className="error">{error}</div>}

      {result && (
        <section className="result-card">
          <h2>AI Result</h2>
          <pre>{result}</pre>
        </section>
      )}
    </div>
  );
}

export default AiToolsPage;
