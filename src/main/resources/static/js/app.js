const { Button, Typography, AppBar, Toolbar, IconButton, MenuIcon } = MaterialUI;

function App() {
  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Login Application
          </Typography>
          <Button color="inherit" href="/logout">Logout</Button>
        </Toolbar>
      </AppBar>
      <div style={{ padding: 16 }}>
        <Typography variant="body1" gutterBottom>
          Welcome to the React Material-UI example.
        </Typography>
        <Button variant="contained" color="primary">
          Click me!
        </Button>
      </div>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("root"));
