import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Card,
  CardContent,
  Button,
  CircularProgress,
  LinearProgress,
  Grid,
  Paper
} from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import { topicService, progressService } from '../services/api';

const TopicView = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [topic, setTopic] = useState(null);
  const [progress, setProgress] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchTopicAndProgress();
  }, [id]);

  const fetchTopicAndProgress = async () => {
    try {
      const userId = localStorage.getItem('userId');
      const [topicResponse, progressResponse] = await Promise.all([
        topicService.getById(id),
        progressService.getSpecificProgress(userId, id)
      ]);
      setTopic(topicResponse.data);
      setProgress(progressResponse.data.percentage || 0);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching topic data:', error);
      setLoading(false);
    }
  };

  const handleCompleteSection = async () => {
    try {
      const userId = localStorage.getItem('userId');
      await progressService.updateProgress(userId, id, 100);
      setProgress(100);
    } catch (error) {
      console.error('Error updating progress:', error);
    }
  };

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <Box sx={{ maxWidth: 1200, margin: '0 auto', p: 3 }}>
      <Typography variant="h4" gutterBottom>
        {topic?.name}
      </Typography>

      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Content
              </Typography>
              <Typography paragraph>
                {topic?.content}
              </Typography>
              
              {topic?.resources?.map((resource, index) => (
                <Paper key={index} sx={{ p: 2, mt: 2 }}>
                  <Typography variant="subtitle1">
                    {resource.title}
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {resource.description}
                  </Typography>
                  <Button
                    href={resource.url}
                    target="_blank"
                    rel="noopener noreferrer"
                    sx={{ mt: 1 }}
                  >
                    Access Resource
                  </Button>
                </Paper>
              ))}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Progress
              </Typography>
              <LinearProgress 
                variant="determinate" 
                value={progress} 
                sx={{ mb: 2 }} 
              />
              <Typography variant="body2" gutterBottom>
                {progress}% Complete
              </Typography>

              {progress < 100 && (
                <Button
                  variant="contained"
                  fullWidth
                  onClick={handleCompleteSection}
                  sx={{ mt: 2 }}
                >
                  Mark as Complete
                </Button>
              )}

              {topic?.assessment && (
                <Button
                  variant="outlined"
                  fullWidth
                  onClick={() => navigate(`/assessment/${topic.assessment.id}`)}
                  sx={{ mt: 2 }}
                >
                  Take Assessment
                </Button>
              )}
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};
export default TopicView;