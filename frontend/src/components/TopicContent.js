import React from 'react';
import {
  Card,
  CardContent,
  Typography,
  Box,
  LinearProgress,
  Button,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

const TopicContent = ({ topic, progress, onComplete }) => {
  const navigate = useNavigate();

  return (
    <Card sx={{ mb: 2 }}>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          {topic.name}
        </Typography>
        <Typography variant="body1" paragraph>
          {topic.description}
        </Typography>
        
        <Box sx={{ mt: 2 }}>
          <Typography variant="body2" gutterBottom>
            Progress: {progress}%
          </Typography>
          <LinearProgress variant="determinate" value={progress} />
        </Box>

        {topic.assessment && (
          <Button
            variant="contained"
            onClick={() => navigate(`/assessment/${topic.assessment.id}`)}
            sx={{ mt: 2 }}
          >
            Take Assessment
          </Button>
        )}

        {progress < 100 && (
          <Button
            variant="outlined"
            onClick={() => onComplete(topic.id)}
            sx={{ mt: 2, ml: 2 }}
          >
            Mark as Complete
          </Button>
        )}
      </CardContent>
    </Card>
  );
};