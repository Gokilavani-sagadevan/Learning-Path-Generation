import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import {
  Login,
  Register,
  Dashboard,
  LearningPaths,
  LearningPathDetails,
  Assessment,
  Profile,
  Certificates,
} from '../pages';

const AppRoutes = () => {
  const { user } = useAuth();

  return (
    <Routes>
      <Route path="/login" element={!user ? <Login /> : <Navigate to="/dashboard" />} />
      <Route path="/register" element={!user ? <Register /> : <Navigate to="/dashboard" />} />
      
      {/* Protected Routes */}
      <Route path="/" element={user ? <Navigate to="/dashboard" /> : <Navigate to="/login" />} />
      <Route path="/dashboard" element={user ? <Dashboard /> : <Navigate to="/login" />} />
      <Route path="/learning-paths" element={user ? <LearningPaths /> : <Navigate to="/login" />} />
      <Route path="/learning-paths/:id" element={user ? <LearningPathDetails /> : <Navigate to="/login" />} />
      <Route path="/assessment/:id" element={user ? <Assessment /> : <Navigate to="/login" />} />
      <Route path="/profile" element={user ? <Profile /> : <Navigate to="/login" />} />
      <Route path="/certificates" element={user ? <Certificates /> : <Navigate to="/login" />} />
      
      {/* Catch all route */}
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  );
};

export default AppRoutes;