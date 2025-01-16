import React, { useState, useEffect } from 'react';
import {
  Typography,
  Box,
  Card,
  CardContent,
  RadioGroup,
  FormControlLabel,
  Radio,
  Button,
  CircularProgress,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions
} from '@mui/material';
import { useParams } from 'react-router-dom';
import { assessmentService } from '../services/api';

const Assessment = () => {
  const { id } = useParams();
  const [assessment, setAssessment] = useState(null);
  const [answers, setAnswers] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitted, setSubmitted] = useState(false);
  const [score, setScore] = useState(null);
  const [showResults, setShowResults] = useState(false);

  useEffect(() => {
    fetchAssessment();
  }, [id]);

  const fetchAssessment = async () => {
    try {
      const response = await assessmentService.getByTopic(id);
      setAssessment(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching assessment:', error);
      setLoading(false);
    }
  };

  const handleSubmit = async () => {
    try {
      const response = await assessmentService.submit({
        assessmentId: id,
        answers: answers
      });
      setScore(response.data.score);
      setShowResults(true);
      setSubmitted(true);
    } catch (error) {
      console.error('Error submitting assessment:', error);
    }
  };

  if (loading) return <CircularProgress />;

  return (
    <Box sx={{ maxWidth: 800, margin: '0 auto' }}>
      <Typography variant="h4" gutterBottom>
        {assessment?.title}
      </Typography>
      
      {assessment?.questions.map((question, index) => (
        <Card key={index} sx={{ mb: 2 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Question {index + 1}
            </Typography>
            <Typography paragraph>{question.text}</Typography>
            
            <RadioGroup
              value={answers[question.id] || ''}
              onChange={(e) => 
                setAnswers({...answers, [question.id]: e.target.value})
              }
            >
              {question.options.map((option, optIndex) => (
                <FormControlLabel
                  key={optIndex}
                  value={option.id}
                  control={<Radio />}
                  label={option.text}
                  disabled={submitted}
                />
              ))}
            </RadioGroup>
          </CardContent>
        </Card>
      ))}

      <Button
        variant="contained"
        color="primary"
        onClick={handleSubmit}
        disabled={submitted}
        sx={{ mt: 2 }}
      >
        Submit Assessment
      </Button>

      <Dialog open={showResults} onClose={() => setShowResults(false)}>
        <DialogTitle>Assessment Results</DialogTitle>
        <DialogContent>
          <Typography variant="h6">
            Your Score: {score}%
          </Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowResults(false)}>Close</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default Assessment;