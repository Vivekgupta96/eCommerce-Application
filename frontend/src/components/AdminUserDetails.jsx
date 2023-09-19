import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../comp_css/AdminUserDetails.css'; 
import api from '../Router/api';

function AdminUserDetails() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get('/ecom/customers/get-all-customer')
      .then((response) => {
        // Sort addresses for each user by timestamp in descending order
        const sortedUsers = response.data.map((user) => ({
          ...user,
          address: user.address.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp)),
        }));
        setUsers(sortedUsers);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
        setLoading(false);
      });
  }, []);

  // Function to get the latest address for a user
  const getLatestAddress = (user) => {
    const addresses = user.address;
    if (addresses && addresses.length > 0) {
      const latestAddress = addresses[0];
      return (
        <div>
          <h3>Latest Address</h3>
          <p>Flat No: {latestAddress.flatNo}</p>
          <p>Street: {latestAddress.street}</p>
          <p>City: {latestAddress.city}</p>
          <p>State: {latestAddress.state}</p>
          <p>Zip Code: {latestAddress.zipCode}</p>
        </div>
      );
    } else {
      return <p>No address available</p>;
    }
  };

  return (
    <div className="admin-users">
      {loading ? (
        <p>Loading...</p>
      ) : (
        users.map((user) => (
          <div className="user-card" key={user.userId}>
            <div className="user-info">
              <h3>User Details</h3>
              <p>User ID: {user.userId}</p>
              <p>Email: {user.email}</p>
              <p>Name: {user.firstName} {user.lastName}</p>
              <p>Phone Number: {user.phoneNumber}</p>
              <p>Register Time: {user.registerTime}</p>
              <p>User Account Status: {user.userAccountStatus}</p>
            </div>
            <div className="user-address">
              {getLatestAddress(user)}
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default AdminUserDetails;
