import React, { useState, useEffect } from 'react';
import {
  Grid,
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
  TextField,
  Box,
  CircularProgress,
  MenuItem,
  Select,
  FormControl,
  InputLabel
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { learningPathService } from '../services/api';

const LearningPaths = () => {
  const [paths, setPaths] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [difficulty, setDifficulty] = useState('all');
  const navigate = useNavigate();

  useEffect(() => {
    fetchLearningPaths();
  }, []);

  const fetchLearningPaths = async () => {
    try {
      const response = await learningPathService.getAll();
      setPaths(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching learning paths:', error);
      setLoading(false);
    }
  };

  const filteredPaths = paths.filter(path => 
    path.title.toLowerCase().includes(searchTerm.toLowerCase()) &&
    (difficulty === 'all' || path.difficulty === difficulty)
  );

  if (loading) return <CircularProgress />;

  return (
    <Box sx={{ flexGrow: 1 }}>
      <Typography variant="h4" gutterBottom>
        Learning Paths
      </Typography>
      
      <Box sx={{ mb: 3, display: 'flex', gap: 2 }}>
        <TextField
          label="Search"
          variant="outlined"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          sx={{ flexGrow: 1 }}
        />
        <FormControl sx={{ minWidth: 120 }}>
          <InputLabel>Difficulty</InputLabel>
          <Select
            value={difficulty}
            label="Difficulty"
            onChange={(e) => setDifficulty(e.target.value)}
          >
            <MenuItem value="all">All</MenuItem>
            <MenuItem value="beginner">Beginner</MenuItem>
            <MenuItem value="intermediate">Intermediate</MenuItem>
            <MenuItem value="advanced">Advanced</MenuItem>
          </Select>
        </FormControl>
      </Box>

      <Grid container spacing={3}>
        {filteredPaths.map((path) => (
          <Grid item xs={12} sm={6} md={4} key={path.id}>
            <Card>
              <CardContent>
                <Typography variant="h6">{path.title}</Typography>
                <Typography variant="body2" color="textSecondary">
                  {path.description}
                </Typography>
                <Typography variant="body2" sx={{ mt: 1 }}>
                  Difficulty: {path.difficulty}
                </Typography>
                <Typography variant="body2">
                  Duration: {path.estimatedHours} hours
                </Typography>
              </CardContent>
              <CardActions>
                <Button 
                  size="small" 
                  color="primary"
                  onClick={() => navigate(`/learning-paths/${path.id}`)}
                >
                  View Details
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default LearningPaths;