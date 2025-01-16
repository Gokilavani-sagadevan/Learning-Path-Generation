import React, { useState, useEffect } from 'react';
import {
  Typography,
  Box,
  Stepper,
  Step,
  StepLabel,
  StepContent,
  Button,
  Paper,
  CircularProgress
} from '@mui/material';
import { useParams } from 'react-router-dom';
import { learningPathService, topicService } from '../services/api';

const LearningPathDetails = () => {
  const { id } = useParams();
  const [path, setPath] = useState(null);
  const [topics, setTopics] = useState([]);
  const [activeStep, setActiveStep] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchPathDetails();
  }, [id]);

  const fetchPathDetails = async () => {
    try {
      const pathResponse = await learningPathService.getById(id);
      const topicsResponse = await topicService.getByLearningPath(id);
      setPath(pathResponse.data);
      setTopics(topicsResponse.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching path details:', error);
      setLoading(false);
    }
  };

  if (loading) return <CircularProgress />;

  return (
    <Box sx={{ maxWidth: 800, margin: '0 auto' }}>
      <Typography variant="h4" gutterBottom>
        {path?.title}
      </Typography>
      <Typography variant="body1" paragraph>
        {path?.description}
      </Typography>
      
      <Box sx={{ mt: 4 }}>
        <Stepper activeStep={activeStep} orientation="vertical">
          {topics.map((topic, index) => (
            <Step key={topic.id}>
              <StepLabel>
                <Typography variant="h6">{topic.name}</Typography>
              </StepLabel>
              <StepContent>
                <Typography>{topic.description}</Typography>
                <Box sx={{ mb: 2 }}>
                  <div>
                    <Button
                      variant="contained"
                      onClick={() => setActiveStep(index + 1)}
                      sx={{ mt: 1, mr: 1 }}
                    >
                      Continue
                    </Button>
                    <Button
                      onClick={() => setActiveStep(index - 1)}
                      sx={{ mt: 1, mr: 1 }}
                      disabled={index === 0}
                    >
                      Back
                    </Button>
                  </div>
                </Box>
              </StepContent>
            </Step>
          ))}
        </Stepper>
      </Box>
    </Box>
  );
};

export default LearningPathDetails;