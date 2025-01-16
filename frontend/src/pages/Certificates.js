import React, { useState, useEffect } from 'react';
import {
  Box,
  Grid,
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
  CircularProgress
} from '@mui/material';
import { Download as DownloadIcon } from '@mui/icons-material';
import { certificateService } from '../services/api';

const Certificates = () => {
  const [certificates, setCertificates] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchCertificates();
  }, []);

  const fetchCertificates = async () => {
    try {
      const userId = localStorage.getItem('userId');
      const response = await certificateService.getUserCertificates(userId);
      setCertificates(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching certificates:', error);
      setLoading(false);
    }
  };

  const handleDownload = async (certificateId) => {
    try {
      const response = await certificateService.download(certificateId);
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `certificate-${certificateId}.pdf`);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      console.error('Error downloading certificate:', error);
    }
  };

  if (loading) {
    return <CircularProgress />;
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <Typography variant="h4" gutterBottom>
        My Certificates
      </Typography>

      <Grid container spacing={3}>
        {certificates.map((certificate) => (
          <Grid item xs={12} sm={6} md={4} key={certificate.id}>
            <Card>
              <CardContent>
                <Typography variant="h6" gutterBottom>
                  {certificate.learningPath.title}
                </Typography>
                <Typography variant="body2" color="textSecondary">
                  Completed: {new Date(certificate.completionDate).toLocaleDateString()}
                </Typography>
                <Typography variant="body2">
                  Certificate Number: {certificate.certificateNumber}
                </Typography>
              </CardContent>
              <CardActions>
                <Button
                  startIcon={<DownloadIcon />}
                  onClick={() => handleDownload(certificate.id)}
                >
                  Download Certificate
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>

      {certificates.length === 0 && (
        <Typography variant="body1" sx={{ mt: 3 }}>
          You haven't earned any certificates yet. Complete a learning path to earn your first certificate!
        </Typography>
      )}
    </Box>
  );
};
export default Certificates;