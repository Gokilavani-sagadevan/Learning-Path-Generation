import React, { useEffect, useState } from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Box,
  LinearProgress
} from '@mui/material';
import { progressService } from '../services/api';

const Dashboard = () => {
  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchDashboardData = async () => {
      try {
        const userId = localStorage.getItem('userId');
        const progress = await progressService.getUserProgress(userId);
        setDashboardData(progress.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching dashboard data:', error);
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <Typography variant="h4" gutterBottom>
        Dashboard
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Current Progress</Typography>
              {dashboardData?.learningPaths?.map((path) => (
                <Box key={path.id} sx={{ mt: 2 }}>
                  <Typography variant="subtitle1">{path.title}</Typography>
                  <LinearProgress 
                    variant="determinate" 
                    value={path.progress} 
                    sx={{ mt: 1 }}
                  />
                  <Typography variant="body2" sx={{ mt: 0.5 }}>
                    {path.progress}% Complete
                  </Typography>
                </Box>
              ))}
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={12} md={6}>
          <Card>
            <CardContent>
              <Typography variant="h6">Recent Assessments</Typography>
              {dashboardData?.recentAssessments?.map((assessment) => (
                <Box key={assessment.id} sx={{ mt: 2 }}>
                  <Typography variant="subtitle1">
                    {assessment.title}
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    Score: {assessment.score}%
                  </Typography>
                </Box>
              ))}
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;