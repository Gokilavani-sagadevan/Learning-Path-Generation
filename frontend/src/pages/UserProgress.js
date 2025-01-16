import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Card,
  CardContent,
  Grid,
  LinearProgress,
  CircularProgress,
  List,
  ListItem,
  ListItemText,
  Divider
} from '@mui/material';
import { progressService } from '../services/api';

const UserProgress = () => {
  const [progressData, setProgressData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUserProgress();
  }, []);

  const fetchUserProgress = async () => {
    try {
      const userId = localStorage.getItem('userId');
      const response = await progressService.getUserProgress(userId);
      setProgressData(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching user progress:', error);
      setLoading(false);
    }
  };

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <Box sx={{ maxWidth: 1200, margin: '0 auto', p: 3 }}>
      <Typography variant="h4" gutterBottom>
        My Progress
      </Typography>

      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Learning Paths Progress
              </Typography>
              {progressData?.learningPaths?.map((path) => (
                <Box key={path.id} sx={{ mb: 3 }}>
                  <Typography variant="subtitle1" gutterBottom>
                    {path.title}
                  </Typography>
                  <LinearProgress 
                    variant="determinate" 
                    value={path.progress} 
                    sx={{ mb: 1 }} 
                  />
                  <Typography variant="body2" color="textSecondary">
                    {path.progress}% Complete
                  </Typography>
                </Box>
              ))}
            </CardContent>
          </Card>
        </Grid>

        <Grid item xs={12} md={4}>
          <Card>
            <CardContent>
              <Typography variant="h6" gutterBottom>
                Recent Activities
              </Typography>
              <List>
                {progressData?.recentActivities?.map((activity, index) => (
                  <React.Fragment key={activity.id}>
                    <ListItem>
                      <ListItemText
                        primary={activity.description}
                        secondary={new Date(activity.date).toLocaleDateString()}
                      />
                    </ListItem>
                    {index < progressData.recentActivities.length - 1 && <Divider />}
                  </React.Fragment>
                ))}
              </List>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};

export default UserProgress;