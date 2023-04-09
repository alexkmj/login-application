const {
  Button,
  Container,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} = MaterialUI;

/**
 * A class component that displays a welcome page.
 * 
 * @class
 * 
 * @author Alex Koh
 * 
 * @extends React.Component
 */
class Welcome extends React.Component {
  /**
   * Constructs the Login component with its initial state.
   * 
   * @param {Object} props - The props object. 
   */
  constructor(props) {
    super(props);

    /**
     * The state object that contains the user's name, username, and whether or
     * not the user is a manager.
     * 
     * @type {Object}
     * 
     * @property {string} name - The user's name.
     * @property {string} username - The user's username.
     * @property {boolean} isManager - Whether or not the user is a manager.
     */
    this.state = {
      name: "",
      username: "",
      isManager: false,
    };
  }

  /**
   * Invoked immediately after the component is mounted. Fetches the user's
   * information from the server.
   */
  componentDidMount() {
    fetch("/api/v1/users", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((data) => {
        this.setState({ name: data.name });
        this.setState({ username: data.username });
        this.setState({ isManager: data.isManager });
      });
  }

  /**
   * Renders the welcome page component
   * 
   * @returns {JSX.Element} The JSX element to render.
   */
  render() {
    return (
      <Container maxWidth="md" sx={{ marginTop: 4 }}>
        <Typography component="h1" variant="h5">
          Welcome {this.state.name}!
        </Typography>
        <TableContainer component={Paper} elevation="3" sx={{ marginTop: 4 }}>
          <Table aria-label="user information">
            <TableHead>
              <TableRow>
                <TableCell sx={{ color: "#5F249F" }}>Key</TableCell>
                <TableCell sx={{ color: "#5F249F" }}>Value</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow key="name">
                <TableCell component="th" scope="row">
                  Name
                </TableCell>
                <TableCell>{this.state.name}</TableCell>
              </TableRow>
              <TableRow key="username">
                <TableCell component="th" scope="row">
                  Username
                </TableCell>
                <TableCell>{this.state.username}</TableCell>
              </TableRow>
              <TableRow key="isManager">
                <TableCell component="th" scope="row">
                  Is Manager
                </TableCell>
                <TableCell>{this.state.isManager ? "Yes" : "No"}</TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
        {this.state.isManager && (
          <Button variant="contained" sx={{ marginTop: 4 }} href="/restricted">
            Restricted Webpage
          </Button>
        )}
      </Container>
    );
  }
}

ReactDOM.render(<Welcome />, document.getElementById("root"));
