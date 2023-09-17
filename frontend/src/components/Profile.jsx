import React, { useState, useEffect } from "react";
import api from '../Router/api'
import "../comp_css/Profile.css";

const userid = localStorage.getItem("userid");

const Profile = () => {
  const [profileData, setProfileData] = useState(null);

  const handlerAddAddress = () => {};
  useEffect(() => {
    api
      .get(`http://127.0.0.1:8080/ecom/customers/${userid}`)
      .then((response) => {
        setProfileData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [userid]);

  const latestAddress = profileData?.address?.length
    ? profileData.address[profileData.address.length - 1]
    : null;

  return (
    <div className="profile-container">
      <div className="profile-details">
        <h1 className="profile-header">Profile Details</h1>
        {profileData ? (
          <>
            <p>
              <strong>Name:</strong> {profileData.firstName}{" "}
              {profileData.lastName}
            </p>
            <p>
              <strong>Email:</strong> {profileData.email}
            </p>

            <p>
              <strong>Phone Number:</strong> {profileData.phoneNumber}
            </p>
            <p>
              <strong>Registration Time:</strong> {profileData.registerTime}
            </p>
            <p style={{ color: "green" }}>
              <strong>Account Status:</strong> {profileData.userAccountStatus}
            </p>
          </>
        ) : (
          <p>Loading profile data...</p>
        )}
      </div>
      <div className="latest-address">
        {latestAddress ? (
          <>
            <h2 className="latest-address-header">Latest Address</h2>
            <p>
              <strong>Street:</strong> {latestAddress.street}
            </p>
            <p>
              <strong>City:</strong> {latestAddress.city}
            </p>
            <p>
              <strong>State:</strong> {latestAddress.state}
            </p>
            <p>
              <strong>Zip Code:</strong> {latestAddress.zipCode}
            </p>
          </>
        ) : (
          <>
            <h2>Add Your Address</h2>
            <button
              onClick={() => {
                handlerAddAddress(userid);
              }}
            >
              Add Address
            </button>
          </>
        )}
      </div>
    </div>
  );
};

export default Profile;
