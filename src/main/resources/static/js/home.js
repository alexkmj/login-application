const { Button, Typography, AppBar, Toolbar, IconButton, MenuIcon } =
  MaterialUI;

class App extends React.Component {
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

ReactDOM.render(<App />, document.getElementById("home"));
