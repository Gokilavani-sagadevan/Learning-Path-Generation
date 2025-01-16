import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const authService = {
  login: (credentials) => api.post('/auth/login', credentials),
  register: (userData) => api.post('/auth/register', userData),
};

export const userService = {
  getUser: (id) => api.get(`/users/${id}`),
  updateUser: (id, data) => api.put(`/users/${id}`, data),
  getAllUsers: () => api.get('/users'),
};

export const learningPathService = {
  getAll: () => api.get('/learning-paths'),
  getById: (id) => api.get(`/learning-paths/${id}`),
  create: (data) => api.post('/learning-paths', data),
  update: (id, data) => api.put(`/learning-paths/${id}`, data),
  delete: (id) => api.delete(`/learning-paths/${id}`),
  search: (keyword) => api.get(`/learning-paths/search?keyword=${keyword}`),
};

export const topicService = {
  getByLearningPath: (learningPathId) => api.get(`/topics/learning-path/${learningPathId}`),
  create: (data) => api.post('/topics', data),
  update: (id, data) => api.put(`/topics/${id}`, data),
  delete: (id) => api.delete(`/topics/${id}`),
};

export const assessmentService = {
  getByTopic: (topicId) => api.get(`/assessments/topic/${topicId}`),
  submit: (data) => api.post('/assessment-attempts', data),
  getAttempts: (userId) => api.get(`/assessment-attempts/user/${userId}`),
};

export const progressService = {
  getUserProgress: (userId) => api.get(`/progress/user/${userId}`),
  updateProgress: (userId, topicId, percentage) => 
    api.post(`/progress/update/${userId}/${topicId}`, { percentage }),
};

export const certificateService = {
  generate: (userId, learningPathId) => 
    api.post(`/certificates/generate/${userId}/${learningPathId}`),
  getUserCertificates: (userId) => api.get(`/certificates/user/${userId}`),
  download: (certificateId) => api.get(`/certificates/download/${certificateId}`),
};

export const searchService = {
    advancedSearch: (keyword, difficulty, minRating) => 
      api.get('/search/advanced', {
        params: {
          keyword,
          difficulty,
          minRating
        }
      }),
    search: (searchParams, pageable) => 
      api.get('/search', { 
        params: {
          ...searchParams,
          ...pageable
        }
      })
  };