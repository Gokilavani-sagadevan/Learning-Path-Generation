import React, { useState, useEffect } from 'react';
import {
  Box,
  Typography,
  Grid,
  Card,
  CardContent,
  CardActions,
  Button,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  CircularProgress
} from '@mui/material';
import { useLocation, useNavigate } from 'react-router-dom';
import { searchService } from '../services/api';
import { useDebounce } from '../hooks/useDebounce';

const SearchResults = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [difficulty, setDifficulty] = useState('all');
  const debouncedSearchTerm = useDebounce(searchTerm, 500);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const initialSearchTerm = params.get('keyword') || '';
    setSearchTerm(initialSearchTerm);
    performSearch(initialSearchTerm);
  }, [location.search]);

  useEffect(() => {
    if (debouncedSearchTerm) {
      performSearch(debouncedSearchTerm);
    }
  }, [debouncedSearchTerm, difficulty]);

  const performSearch = async (keyword) => {
    try {
      setLoading(true);
      const response = await searchService.advancedSearch(keyword, difficulty);
      setSearchResults(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error performing search:', error);
      setLoading(false);
    }
  };

  return (
    <Box sx={{ maxWidth: 1200, margin: '0 auto', p: 3 }}>
      <Typography variant="h4" gutterBottom>
        Search Results
      </Typography>

      <Box sx={{ mb: 4 }}>
        <Grid container spacing={2} alignItems="center">
          <Grid item xs={12} md={6}>
            <TextField
              fullWidth
              label="Search"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </Grid>
          <Grid item xs={12} md={6}>
            <FormControl fullWidth>
              <InputLabel>Difficulty Level</InputLabel>
              <Select
                value={difficulty}
                label="Difficulty Level"
                onChange={(e) => setDifficulty(e.target.value)}
              >
                <MenuItem value="all">All Levels</MenuItem>
                <MenuItem value="beginner">Beginner</MenuItem>
                <MenuItem value="intermediate">Intermediate</MenuItem>
                <MenuItem value="advanced">Advanced</MenuItem>
              </Select>
            </FormControl>
          </Grid>
        </Grid>
      </Box>

      {loading ? (
        <CircularProgress />
      ) : (
        <Grid container spacing={3}>
          {searchResults.map((result) => (
            <Grid item xs={12} sm={6} md={4} key={result.id}>
              <Card>
                <CardContent>
                  <Typography variant="h6" gutterBottom>
                    {result.title}
                  </Typography>
                  <Typography variant="body2" color="textSecondary" paragraph>
                    {result.description}
                  </Typography>
                  <Typography variant="body2">
                    Difficulty: {result.difficulty}
                  </Typography>
                  <Typography variant="body2">
                    Duration: {result.estimatedHours} hours
                  </Typography>
                </CardContent>
                <CardActions>
                  <Button
                    size="small"
                    color="primary"
                    onClick={() => navigate(`/learning-paths/${result.id}`)}
                  >
                    View Details
                  </Button>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}

      {!loading && searchResults.length === 0 && (
        <Typography variant="body1" sx={{ mt: 3 }}>
          No results found for your search criteria. Try adjusting your search terms or filters.
        </Typography>
      )}
    </Box>
  );
};

export default SearchResults;
