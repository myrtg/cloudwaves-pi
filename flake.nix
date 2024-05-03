{
  description = "Nix Shell for DevOps";
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/release-23.11";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    { nixpkgs
    , flake-utils
    , ...
    }:
    flake-utils.lib.eachDefaultSystem (system:
    let
      pkgs = import nixpkgs { inherit system; };
    in
    {
      devShells.default = pkgs.mkShell {
        packages = with pkgs;[
          ansible
          ctop
          kubectl
          minikube
          taplo
          yamllint
        ];
        # shellHook = ''
        # '';
      };
    }
    );
}
