import React, { useState, useEffect } from "react";
import api from "../Router/api";
import "../comp_css/Profile.css";
import Address from "../components/Address";
import UpdateAddress from "../components/UpdateAddress";

const userid = localStorage.getItem("userid");
const passData={
  newpass:""
}



const Profile = () => {
  const [profileData, setProfileData] = useState(null);
  const [add, setAdd] = useState(null);
  const [addressModal, setAddressModal] = useState(false);
  const [updateaddressModal, setUpdateAddressModal] = useState(false);
  const [showPassSection, setShowPassSection] = useState(false);
  const [passform, setNewPassword1] = useState("");
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const val = e.target.value;
    setNewPassword1({ ...passform, [e.target.name]: val });
  };

  const handleSubmit = (e) => {
    
    e.preventDefault();

    api
      .put(`/ecom/customers/update-password/${userid}`, passform)
      .then((response) => {
        alert("Password updated successfully");
        setShowPassSection(false);
      })
      .catch((error) => {
        alert("Error occures Try letter....")
      });
  };
  const changePassword = () => {
    setShowPassSection(true);
  };

  const handlerUpdateAddress = (latestAddress) => {
    setAdd(latestAddress);
    setUpdateAddressModal(true);
  };

  const showUpdateAddAddressModal = () => {
    setUpdateAddressModal(false);
  };
  const showAddAddressModal = () => {
    setAddressModal(false);
  };
  const handlerAddAddress = (userid) => {
    setAddressModal(true);
    console.log("called...... show");
  };

  useEffect(() => {
    api
      .get(`/ecom/customers/${userid}`)
      .then((response) => {
        setProfileData(response.data);
        setAddressModal(false);
      })
      .catch((error) => {
        console.error("Error fetching data from the API: ", error);
      });
  }, [userid]);


  const {newpass}=passform;
  const latestAddress = profileData?.address?.length
    ? profileData.address[profileData.address.length - 1]
    : null;

  return (
    <>
      <h2
        style={{
          color: "green",
          textAlign: "center",
          margin: "20px",
        }}
      >
        Profile Section
      </h2>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "flex-start",
        }}
      >
        <div className="profile-container">
          {addressModal && <Address onclose={showAddAddressModal} />}
          {updateaddressModal && (
            <UpdateAddress address={add} onclose={showAddAddressModal} />
          )}

          <div className="profile-details">
            <h1 className="profile-header">Profile Details</h1>

            {profileData ? (
              <>
                <p style={{ color: "green" }}>
                  <strong>Account Status:</strong>{" "}
                  {profileData.userAccountStatus}
                </p>
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
                  <strong>Registration :</strong>{" "}
                  {profileData.registerTime.substring(0, 10)}
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
                  <strong>Buiding :</strong> {latestAddress.flatNo}
                </p>
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

                <button
                  onClick={() => {
                    handlerUpdateAddress(latestAddress);
                  }}
                >
                  Update Address
                </button>
              </>
            ) : (
              <>
                <h2>Address Not updated</h2>
                <button
                  onClick={() => {
                    handlerAddAddress();
                  }}
                >
                  Add Address
                </button>
              </>
            )}
          </div>
        </div>
        <div className="updatePassword">
          {showPassSection ? (
            <form onSubmit={handleSubmit}>
              <label htmlFor="newPassword">New Password:</label>
              <input
                type="password"
                name="newPassword"
                value={newpass}
                onChange={handleChange}
              />
              {error && <p className="error">{error}</p>}
              <button type="submit">Update Password</button>
              <button
                type="button"
                onClick={() => {
                  setShowPassSection(false);
                }}
              >
                Cancel update
              </button>
            </form>
          ) : (
            <button
              onClick={() => {
                changePassword();
              }}
            >
              ChangePassword
            </button>
          )}
        </div>
      </div>
    </>
  );
};

export default Profile;
