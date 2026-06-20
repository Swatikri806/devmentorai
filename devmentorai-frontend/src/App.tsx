import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import DashboardPage from './pages/DashboardPage';
import AiToolsPage from './pages/AiToolsPage';
import { getToken } from './utils/auth';
import './App.css';

function App() {
  const isAuthenticated = Boolean(getToken());

  return (
    <div className="app-shell">
      <Routes>
        <Route path="/" element={isAuthenticated ? <Navigate to="/dashboard" /> : <Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard" element={isAuthenticated ? <DashboardPage /> : <Navigate to="/login" />} />
        <Route path="/ai" element={isAuthenticated ? <AiToolsPage /> : <Navigate to="/login" />} />
      </Routes>
    </div>
  );
}

export default App;
