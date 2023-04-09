const { Typography } = MaterialUI;

/**
 * App component that renders the homepage of the login application.
 * 
 * @class
 * 
 * @author Alex Koh
 * 
 * @extends React.Component
 */
class Home extends React.Component {
  render() {
    return (
      <div style={{ padding: 16 }}>
        <Typography variant="body1" gutterBottom>
          Welcome to the Login Application.
        </Typography>
      </div>
    );
  }
}

ReactDOM.render(<Home />, document.getElementById("home"));
