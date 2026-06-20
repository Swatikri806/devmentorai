import { useEffect, useState } from 'react';
import { clearToken } from '../utils/auth';
import { useNavigate, Link } from 'react-router-dom';
import api from '../api';

function DashboardPage() {
  const [profile, setProfile] = useState<{ username: string; fullName: string; email: string; role: string } | null>(null);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    async function loadProfile() {
      try {
        const response = await api.get('/profile');
        setProfile(response.data);
      } catch (err) {
        setError('Could not load profile.');
      }
    }
    loadProfile();
  }, []);

  const logout = () => {
    clearToken();
    navigate('/login');
  };

  return (
    <div className="panel">
      <header className="page-header">
        <div>
          <h1>Welcome to DevMentorAI</h1>
          <p>Use AI to review your resume, code, and interview prep.</p>
        </div>
        <div>
          <button onClick={logout}>Logout</button>
        </div>
      </header>
      {error && <div className="error">{error}</div>}
      {profile && (
        <section className="profile-card">
          <h2>Your profile</h2>
          <p><strong>Name:</strong> {profile.fullName}</p>
          <p><strong>Username:</strong> {profile.username}</p>
          <p><strong>Email:</strong> {profile.email}</p>
          <p><strong>Role:</strong> {profile.role}</p>
        </section>
      )}
      <section className="feature-grid">
        <Link to="/ai" className="feature-card">
          <h3>AI Tools</h3>
          <p>Resume reviews, code evaluation, and interview question generation.</p>
        </Link>
      </section>
    </div>
  );
}

export default DashboardPage;
