import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import api from '../api';
import { setToken } from '../utils/auth';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await api.post('/auth/login', { username, password });
      setToken(response.data.accessToken);
      navigate('/dashboard');
    } catch (err) {
      setError('Login failed. Please check your credentials.');
    }
  };

  return (
    <div className="panel">
      <h1>DevMentorAI</h1>
      <p>Smart career coaching and AI review platform.</p>
      <form onSubmit={handleSubmit}>
        <label>
          Username
          <input value={username} onChange={(e) => setUsername(e.target.value)} required />
        </label>
        <label>
          Password
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </label>
        {error && <div className="error">{error}</div>}
        <button type="submit">Login</button>
      </form>
      <p>
        New here? <Link to="/register">Create account</Link>
      </p>
    </div>
  );
}

export default LoginPage;
